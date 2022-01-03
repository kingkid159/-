import os
import sys
import urllib.request
import json
import requests
from bs4 import BeautifulSoup
a='컴퓨터 스피커'
client_id = "7g7soFD2qJdLYdDoX1ZW"
client_secret = "71ij9z3AHo"
encText = urllib.parse.quote("{}".format(a))
url = "https://openapi.naver.com/v1/search/shop.json?query=" + encText # json 결과
# url = "https://openapi.naver.com/v1/search/blog.xml?query=" + encText # xml 결과
request = urllib.request.Request(url)
request.add_header("X-Naver-Client-Id",client_id)
request.add_header("X-Naver-Client-Secret",client_secret)
response = urllib.request.urlopen(request)
rescode = response.getcode()
if(rescode==200):
    response_body = response.read()
    j1=(response_body.decode('utf-8'))
else:
    print("Error Code:" + rescode)
data=json.loads(j1)
print(data)
items=data['items']
with open('data\\{}.csv'.format(a),'w',encoding='utf-8')as f:
    for i in items:
        title=i['title']
        link=i['link']
        img=i['image']
        maker=i['maker']
        price=i['lprice']
        print(title)
        price=int(price)
        print(price)
        print(link)
        print(img)
        print(maker)
        f.write('title::link::img::maker::price\n')
    # result=requests.get(link)
    # dom=BeautifulSoup(result.text,'lxml')
    # price=dom.select_one('#__next > div > div.product_bridge_product__HdCK7 > p')
    # print(price.text)