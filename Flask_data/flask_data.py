from flask import Flask

app = Flask(__name__, static_url_path='/resource')

@app.route("/test")
def spring ():
    return "<h1>Flask Server<h1>"

@app.route("/data")
def data():
    import pandas as pd
    import numpy as np

    m_store = pd.read_csv('C:\git\Parking_management_Project\Flask_data\source\주현아파트.csv')

    m_store.loc[m_store['차량 수'] == "", '설명'] = '차량 수 등록이 안 되어 있습니다.'
    m_store.loc[m_store['차량 수'] == 1, '설명'] = '차량 한 대 중 첫 번째 차량입니다.'
    m_store.loc[m_store['차량 수'] == 2.1, '설명'] = '차량 두 대 중 첫 번째 차량입니다.'
    m_store.loc[m_store['차량 수'] == 2.2, '설명'] = '차량 두 대 중 두 번째 차량입니다.'
    return m_store.to_html()


if __name__ == '__main__':
    app.run(host="127.0.0.1")