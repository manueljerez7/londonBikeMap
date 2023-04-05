import json
import requests

def getBikePoints():
    response = requests.get("http://localhost:8080/BikePoint/")
    data = response.json()

    for i in range(len(data)):
        if(int(data[i]["NbDocks"]) != 0 ):
            perc = int(data[i]["NbBikes"])/int(data[i]["NbDocks"])
        else:
            perc = 0

        if(perc > 0.9):
            data[i]['color'] = 0
        else: 
            if(perc > 0.5):
                data[i]['color'] = 1
            else:
                if(perc > 0):
                    data[i]['color'] = 2
                else:
                    if(perc == 0):
                        data[i]['color'] = 3
    return data