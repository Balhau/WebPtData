from datetime import timedelta, date
import urllib.request

def baseUrl(dataPublicacaoInit,dataPublicacaoEnd):
  return f'http://www.base.gov.pt/base2/rest/contratos.csv?texto=&tipo=0&tipocontrato=0&cpv=&numeroanuncio=&aqinfo=&adjudicante=&adjudicataria=&desdeprecocontrato_false=&desdeprecocontrato=&ateprecocontrato_false=&ateprecocontrato=&desdedatacontrato=&atedatacontrato=&desdedatapublicacao={dataPublicacaoInit}&atedatapublicacao={dataPublicacaoEnd}&desdeprazoexecucao=&ateprazoexecucao=&desdedatafecho=&atedatafecho=&desdeprecoefectivo_false=&desdeprecoefectivo=&ateprecoefectivo_false=&ateprecoefectivo=&pais=0&distrito=0&concelho=0&sort(-publicationDate)'

def date_range_generator(start_date, end_date):
    for n in range(int ((end_date - start_date).days)):
        yield start_date + timedelta(n)

def date_range_array(start_date,end_date):
    months=[]
    for n in range(int ((end_date - start_date).days)):
        months.append(start_date + timedelta(n))
    return months

start_date = date(2000, 1, 1)
end_date = date(2020, 6, 2)

date_range_generator=date_range_generator(start_date, end_date)
date_range_array=date_range_array(start_date,end_date)

for index in range(0,len(date_range_array)):
    if index < len(date_range_array):
      prev_month=date_range_array[index].strftime("%Y-%m-%d")
      next_month=date_range_array[index].strftime("%Y-%m-%d")
      contents = urllib.request.urlopen(baseUrl(prev_month,next_month)).read()
      print(contents.decode('utf-8'))
