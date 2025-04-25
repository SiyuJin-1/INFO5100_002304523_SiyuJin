from PIL import Image, ImageFilter
import numpy as np
from scipy.ndimage import rotate
import tensorflow as tf

# Convert image to grayscale
img = Image.open("digit_input.png").convert("L")

# Crop the non-black parts of the image
img = img.crop(img.getbbox())
w, h = img.size

scale = min(20/w, 20/h)
new_w, new_h = int(w*scale), int(h*scale)
img = img.resize((new_w, new_h), Image.Resampling.NEAREST)

# Place the image to center
new_img = Image.new("L", (28, 28), color=0)
new_img.paste(img, ((28-new_w)//2, (28-new_h)//2))

# tilt correction
img_array = np.array(img)
coords = np.column_stack(np.where(img_array > 0))
if len(coords) > 0:
    cov = np.cov(coords.T)
    v = np.linalg.eigh(cov)[1]
    angle = np.degrees(np.arctan2(v[1, 0], v[0, 0]))
    rotated = rotate(img_array, angle, reshape=False)
    img = Image.fromarray(rotated)

# Format change
arr = np.array(new_img)
arr = arr.reshape(-1, 28, 28, 1).astype("float32")/ 255.0

# Loading the model & prediction
model = tf.keras.models.load_model("digit_model.keras")
pred = model.predict(arr)
probabilities = pred[0]
for i, p in enumerate(probabilities):
    print(f"Digit {i}: {p:.4f}")
print(np.argmax(pred), flush=True)