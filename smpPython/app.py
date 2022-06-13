from flask import Flask
import test as check

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/test')
def test():
    return check.testCheck()


if __name__ == '__main__':
    app.run()
