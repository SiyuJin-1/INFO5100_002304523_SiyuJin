# INFO5100 Final Project Image Management Tool

A JavaFX-based desktop application for managing images. Users can upload images, preview thumbnails, convert between image formats (PNG, JPG, BMP, GIF), and apply image filters such as **black-and-white** and **inverted colors**.

---

## 📦 Features

- 📤 Upload local images
- 🖼️ Generate and preview image thumbnails
- 📑 View image metadata (filename, resolution, size, format, modified date)
- 🔄 Convert images to different formats: PNG, JPG, BMP, GIF
- 🎨 Apply filters:
  - Black & White
  - Invert colors
- 💾 Save converted images to computer

---

## 🎯 Technologies Used

- **JavaFX** for GUI (Java SDK 24.0.1)
- **Java AWT & Swing** for image processing
- **use of inheritance, encapsulation and interfaces**
  - **Inheritance**
    - Both the BlackWhiteFilter and InvertFilter classes inherit from the Filter interface and implement the apply(BufferedImage) method
    - ImageTool inherits from the Application class of JavaFX and is used for GUI startup
  - **Encapsulation**
    - Each class is responsible for a specific duty, such as:
      - Convert: Handle image format conversion and filter conversion;
      - ImageFiles: Provide image file information (such as BufferedImage original image, width and height, format, etc.);
      - Download and Upload: Download and Upload files;
      - InvertFilter: Invert images;
      - BlackWhiteFilter: Convert images to Black-White format.
  - **Interfaces**
    - Filter is a custom interface that defines a image processing method
    - BlackWhiteFilter and InvertFilter implement this interface, allowing for flexible use in FilterFactory
- **Design Patterns**:
  - `Filter` Interface (Strategy Pattern)
  - `FilterFactory` (Factory Method Pattern)

---

## 🗂️ Project Structure(Class Diagram)



## 🚀 How to Run

### Prerequisites

- JavaFX SDK 24.0.1  
  [Download JavaFX SDK](https://openjfx.io/)

### Steps




