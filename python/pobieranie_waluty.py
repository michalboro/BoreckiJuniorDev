import requests
#import pprint
import mysql.connector 
import pandas as pd
#from xlsxwriter import Workbook
import logging

logging.basicConfig(filename='log_file.log', level=logging.DEBUG)

#logging.debug('debug level log asdfasdfas')

try:
    connection = mysql.connector.connect(user='root', password='roo', host='127.0.0.1', database='mydb', auth_plugin='mysql_native_password')
except Exception as ex:
        logging.warning(ex)
        print ('Database connection ERROR') #dopisać exit

try:       
    usdRatesResponse = requests.get('http://api.nbp.pl/api/exchangerates/rates/a/usd/')
#except Exception as ex:
 #       logging.warning(ex)    
#pprint.pprint(r.json()['rates'][0]['mid']) 


#try:
    eurRatesResponse = requests.get('http://api.nbp.pl/api/exchangerates/rates/a/eur/')
except Exception as e:
        logging.warning(e)
        print('API connection ERROR') #exit

usd = usdRatesResponse.json()['rates'][0]['mid']        
#pprint.pprint(p.json()['rates'][0]['mid'])  
eur = eurRatesResponse.json()['rates'][0]['mid']
#print (usd)
#print (eur)


query = 'UPDATE Product SET UnitPriceUSD = (UnitPrice * %s)'
query1 = 'UPDATE Product SET UnitPriceEuro = (UnitPrice * %s)'

#query1 = 'SELECT * FROM Product'

cursor = connection.cursor()
cursor.execute(query,(usd, ))
cursor.execute(query1,(eur, ))
#for row in cursor:
    #print(row)

connection.commit()



sql = 'select ProductID,DepartmentID,Category,IDSKU,ProductName,Quantity,UnitPrice,UnitPriceUSD,UnitPriceEuro,Ranking,ProductDesc,UnitsInStock,UnitsInOrder,UnitPriceUSD,UnitPriceEuro from Product'

filePath= '/home/janek/Pulpit/python_junior/plik_exel.xlsx'

writer= pd.ExcelWriter(filePath,engine='xlsxwriter')

df = pd.read_sql(sql,connection)
#df

for table_name in df:
   sheet_name = table_name
   df.to_excel(writer, sheet_name=sheet_name, index=False) 


writer.save()    
connection.close()