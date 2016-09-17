from flask import Flask
from waitress import serve
from twilio.rest import TwilioRestClient


app = Flask(__name__)


@app.route("/")

def index():

    return "Hello World!"


@app.route("/helloworld/")

def twilioRun():


    account_sid = "AC3ceb5731f004a47d4232bbb946e460b3"

    auth_token  = "b51d78fa9c7bbb9ba1b134ad1ca19e27"

    client = TwilioRestClient(account_sid, auth_token)

    message = client.messages.create(body="Hello Isaac",

                                     to="+12405053437",

                                     from_="+15854548472")


    return message.sid





if __name__ == "__main__":
    if os.environ['PRODUCTION'] == "0":
	app.run(host='0.0.0.0', port=port)
    else:
	serve(app, port=port)
    #app.run()
