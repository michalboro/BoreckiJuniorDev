from json import load
import requests
import mysql.connector 
import pandas as pd
import logging
import sys

logging.basicConfig(filename='log_file.log', level=logging.DEBUG)

class NBPApi:
    def fetchExchangeRates(self):
        try:
            self.baseURL = 'http://api.nbp.pl'
            usdRatesResponse = requests.get(self.baseURL + '/api/exchangerates/rates/a/usd/')
            eurRatesResponse = requests.get(self.baseURL + '/api/exchangerates/rates/a/eur/')
            usd = usdRatesResponse.json()['rates'][0]['mid']
            eur = eurRatesResponse.json()['rates'][0]['mid']      
        
            return [
                usd, 
                eur
            ]
        except Exception as exApi:
            logging.warning(exApi)
            print('API connection ERROR')      


class DatabaseManager:
    def __init__(self):
        try:
            self.connection = mysql.connector.connect(user='root', password='root', host='127.0.0.1', database='mydb', auth_plugin='mysql_native_password')
        except Exception as ex:
                logging.warning(ex)
                print('Database connection ERROR')
                sys.exit(1)

    def updateProductPrices(self, eur, usd):
        try:
            query = 'UPDATE Product SET UnitPriceUSD = (UnitPrice * %s)'
            query1 = 'UPDATE Product SET UnitPriceEuro = (UnitPrice * %s)'

            cursor = self.connection.cursor()
            cursor.execute(query,(usd, ))
            cursor.execute(query1,(eur, ))

            self.connection.commit()
        except Exception as exUpdate:
                logging.warning(exUpdate)
                print('Database update ERROR')
    

    def getProducts(self):
        sql = 'select ProductID,DepartmentID,Category,IDSKU,ProductName,Quantity,UnitPrice,UnitPriceUSD,UnitPriceEuro,Ranking,ProductDesc,UnitsInStock,UnitsInOrder from Product'

        return pd.read_sql(sql, self.connection)

    def closeConnection(self):
        self.connection.close()

class ProductToExcelWriter:
    def __init__(self):
        self.filePath= '/home/janek/Pulpit/python_junior/excel_file.xlsx'
        self.writer = pd.ExcelWriter(self.filePath,engine='xlsxwriter')

    def writeProduct(self, productDataFrame):
        try:
            for table_name in productDataFrame:
                sheet_name = table_name
                productDataFrame.to_excel(self.writer,sheet_name=sheet_name, index=False)
                self.writer.save()
        except Exception as exWriter:
            logging.warning(exWriter)
            print('Writing to Excel ERROR')        
           
nbpApi = NBPApi()
exchangeRates = nbpApi.fetchExchangeRates()

databaseManager = DatabaseManager()
databaseManager.updateProductPrices(exchangeRates[0], exchangeRates[1])

df = databaseManager.getProducts()

writtingToExcel = input('Zapisać listę produktów do pliku .xlsx? t/n: ')
if writtingToExcel == 't':
    productToExcelWriter = ProductToExcelWriter()
    productToExcelWriter.writeProduct(df)

databaseManagerClose = DatabaseManager()
databaseManagerClose.closeConnection()