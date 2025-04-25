import tensorflow as tf
from tensorflow.keras import layers, models
from tensorflow.keras.datasets import mnist
from tensorflow.keras.utils import to_categorical
from tensorflow.keras.layers import BatchNormalization, Dropout

# Load MNIST dataset(Each image is a 28x28 black-and-white image, representing a handwritten digit (0 to 9).)
(x_train, y_train), (x_test, y_test) = mnist.load_data()

# Preprocess the data, normalize and reshape
# Transform the original two-dimensional image into a four-dimensional tensor
# -1 indicates the automatic calculation of the sample size and the last 1 represents the grayscale image (with only one channel)
x_train = x_train.reshape(-1, 28, 28, 1).astype("float32") / 255.0
x_test = x_test.reshape(-1, 28, 28, 1).astype("float32") / 255.0

# Convert labels to one-hot encoding
# The original label is a number from 0 to 9, and the one-hot encoding will convert it into a vector of length 10
# For example, the label 3 will be converted to [0, 0, 0, 1, 0, 0, 0, 0, 0, 0]
y_train = to_categorical(y_train, 10)
y_test = to_categorical(y_test, 10)

# Model construction
model = models.Sequential([
    #Extract local image features
    layers.Conv2D(32, (3, 3), activation='relu', input_shape=(28, 28, 1)), 
    BatchNormalization(),
    # Dimensionality reduction
    layers.MaxPooling2D((2, 2)), 
    layers.Conv2D(64, (3, 3), activation='relu'),
    BatchNormalization(),
    layers.MaxPooling2D((2, 2)),
    # Convert the 2D image into a 1D vector to be sent to the fully connected layer
    layers.Flatten(), 
    layers.Dense(64, activation='relu'),
    Dropout(0.3),
    # Convert the result into a probability value (the maximum value is the predicted number)
    layers.Dense(10, activation='softmax') 
])

# Model compile
model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# Model training, train five times to traverse all samples and each time use 64 samples for training
model.fit(x_train, y_train, epochs=10, batch_size=64, validation_split=0.1)

# Model evaluation
test_loss, test_acc = model.evaluate(x_test, y_test)
print(f"Test accuracy: {test_acc:.4f}")

# Save the model in TensorFlow's SavedModel format
model.save("digit_model.keras")

