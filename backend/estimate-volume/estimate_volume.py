import math

import cv2
import numpy as np
from PIL import Image


class EstimateVolume:
    COIN = 2.4
    MAX_SIZE = 500

    def __init__(self, image1, image2):
        self.image1 = self._load_image(image1)
        self.image2 = self._load_image(image2)

    def _load_image(self, image):
        image = np.array(Image.open(image))
        width, height = image.shape[1::-1]
        size = max(width, height)
        scale = 500 / size
        return cv2.resize(image, (int(width * scale), int(height * scale)))

    def estimate(self):
        a, b = self._estimate_dimensions(self.image1)
        c, d = self._estimate_dimensions(self.image2)
        if abs(a - c) < abs(a - d) and abs(a - c) < abs(b - d):
            return a, b, d
        if abs(b - c) < abs(b - d) and abs(b - c) < abs(a - d):
            return a, b, d
        return a, b, c

    def _estimate_dimensions(self, image):
        gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
        blur = cv2.GaussianBlur(gray, (7, 7), 0)
        edges = self._find_edges(blur)
        contours = self._find_contours(edges)
        result = image.copy()
        cm_per_pixel = None
        for contour in contours:
            if cv2.contourArea(contour) < 100:
                continue
            box = cv2.minAreaRect(contour)
            box = cv2.boxPoints(box)
            box = np.array(box, dtype="int")
            cv2.drawContours(result, [box.astype("int")], -1, (0, 255, 0), 2)
            width, height = self._dimension(box)
            if cm_per_pixel is None:
                cm_per_pixel = (width + height) / 2 / self.COIN
            else:
                return width / cm_per_pixel, height / cm_per_pixel
        return 0, 0

    def _find_edges(self, image):
        edged = cv2.Canny(image, 20, 70)
        edged = cv2.dilate(edged, None, iterations=10)
        return cv2.erode(edged, None, iterations=10)

    def _find_contours(self, image):
        _, contours, *_ = cv2.findContours(image.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
        return sorted(contours, key = cv2.contourArea)

    def _show_image(self, image):
        while cv2.waitKey(10) != 27:
            cv2.imshow("Preview", image)

    def _dimension(self, box):
        a, b, c, d = box
        width, height, hypot = sorted(self._distance(a, p) for p in (b, c, d))
        return width, height

    def _distance(self, a, b):
        return math.sqrt((b[0] - a[0]) ** 2 + (b[1] - a[1]) ** 2)