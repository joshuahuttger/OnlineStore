import requests
import json
products = [
    ["the bible", "the holy bible", "100"],
    ["chicken tenders", "yummy battered chicken tenders", "1000"],
    ["mountain bike", "awesome mountain bike", "100000"]
]


def api_post(endpoint_url, data):
    headers = {'Accept' : 'application/json', 'Content-Type' : 'application/json'}
    return requests.post(url=endpoint_url, data=json.dumps(data), headers=headers).content


url = "http://localhost:8080/products"
for i in products:
    newProduct = dict()
    newProduct["name"] = i[0]
    newProduct["description"] = i[1]
    newProduct["price"] = i[2]
    print("adding " + str(newProduct))
    print("results = " + str(api_post(url, newProduct)))