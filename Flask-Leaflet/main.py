from flask import Flask, render_template, request
from getBikePoints import getBikePoints

app=Flask(__name__)

@app.route('/')
def root():
    markers=getBikePoints()
    return render_template('index.html',markers=markers )

if __name__ == '__main__':
    app.run(host="localhost", port=25000, debug=True)
