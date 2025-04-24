# Final Project - Image Management Tool & Number Recognizer

This final project includes **both Option #1 (Image Management Tool)** and **Option #2 (Number Recognizer)**.

## What’s Included

### Option #1: Image Management Tool (Main Focus)

This project allows users to upload image files, display thumbnails, view image properties (such as size, format, etc.), convert images to different formats (JPG, PNG, BMP, GIF), and apply visual filters like **grayscale (black and white)** and **invert colors**.  
It also supports downloading the converted results and uses **Object-Oriented Programming** principles and the **Factory Design Pattern** for filter selection.

---

### 🧪 Option #2: Number Recognizer (Extra Work)

Initially, I planned to implement Option #2 using JavaFX with TensorFlow Java API. However, I found that **TensorFlow is significantly easier to handle in Python**, especially for training and prediction tasks.  
So I used **Python** to build and train the model (using MNIST), and only used Java to design the GUI and call the trained model via an external prediction service.

> ⚠️ Since most part of the solution uses Python, it may **not fully meet the Java-only expectation**, so I also implement Option #1.
