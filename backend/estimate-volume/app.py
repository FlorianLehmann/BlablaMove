import os

from flask import Flask, flash, request, redirect, render_template
from flask.json import jsonify

from estimate_volume import EstimateVolume

app = Flask(__name__)
app.secret_key = "secret_key"

ALLOWED_EXTENSIONS = {'png', 'jpg', 'jpeg', 'bmp'}
UPLOAD_FOLDER = 'uploads'
if UPLOAD_FOLDER and not os.path.exists(UPLOAD_FOLDER):
    os.mkdir(UPLOAD_FOLDER)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


def allowed_file(filename):
    return '.' in filename and filename.split('.')[-1].lower() in ALLOWED_EXTENSIONS


@app.route('/', methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        if 'image1' not in request.files or 'image2' not in request.files:
            flash('One image is missing')
            return jsonify(error="One image is missing. Be sure to send the 'image1' and 'image2'"), 400
        image1 = request.files['image1']
        image2 = request.files['image2']
        if image1 and allowed_file(image1.filename) and image2 and allowed_file(image2.filename):
            return jsonify(estimation=estimate_volume(image1, image2))
        return jsonify(error="Image does not have valid filename"), 400
    return render_template("index.html")


def estimate_volume(image1, image2):
    width, height, depth = EstimateVolume(image1, image2).estimate()
    return {"width": width, "height": height, "depth": depth, "volume": width * height * depth, "unit": "cm"}


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
