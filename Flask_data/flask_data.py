from flask import Flask

app = Flask(__name__)

@app.route("/test")
def spring ():
    return "<h1>Flask Server<h1>"


if __name__ == '__main__':
    app.run(host="127.0.0.1")