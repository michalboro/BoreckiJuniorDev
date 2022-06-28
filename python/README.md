# ZADANIE – JUNIOR PYTHON DEVELOPER

### Skrypt dostępny jest w postaci paczki python 

JuniorPythonDeveloperMichalBorecki-1.0-py2.py3-none-any.whl

### API NBP

Do pobrania danych ze strony używam: 

```

 usdRatesResponse = requests.get(self.baseURL + '/api/exchangerates/rates/a/usd/')
 eurRatesResponse = requests.get(self.baseURL + '/api/exchangerates/rates/a/eur/')
 
```            
Wartości zapisane do zmiennych:

```

usd = usdRatesResponse.json()['rates'][0]['mid']
eur = eurRatesResponse.json()['rates'][0]['mid']

```
### DATABASE MANAGER

Łączenie z lokalną bazą danych:

```

 self.connection = mysql.connector.connect(user='root', password='root', host='127.0.0.1', database='mydb', auth_plugin='mysql_native_password')
 
```
Aktualizacja danych w bazie danych na podstawie pobranych wartości:

```

query = 'UPDATE Product SET UnitPriceUSD = (UnitPrice * %s)'
query1 = 'UPDATE Product SET UnitPriceEuro = (UnitPrice * %s)'

cursor = self.connection.cursor()
cursor.execute(query,(usd, ))
cursor.execute(query1,(eur, ))
            
```
Zapis danych do pliku .xlsx:

```

self.filePath= '/home/janek/Pulpit/python_junior/excel_file.xlsx'
self.writer = pd.ExcelWriter(self.filePath,engine='xlsxwriter')

for table_name in productDataFrame:
    sheet_name = table_name
    productDataFrame.to_excel(self.writer,sheet_name=sheet_name, index=False)
    self.writer.save() 
```    
### CYKLICZNOŚĆ 

```
schedule.every().day.at("10:00").do(updateNow) 

```
