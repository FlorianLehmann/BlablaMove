import unittest

import requests


rubiks_front = "examples/rubiks-front-50x50x34.JPG"
rubiks_side = "examples/rubiks-side-50x50x34.JPG"
phone_box_front = "examples/phone-box-front-157x85x47.JPG"
phone_box_side = "examples/phone-box-side-157x85x47.JPG"

class Test(unittest.TestCase):
    """
        We test the accuracy of the prediction based on the largest dimension.
        So for example, for an object of size 18x11x5, the accuracy is calculated using the side of length 18.
    """
    accuracy = 0.2

    def test_rubiks(self):
        with open(rubiks_front, 'rb') as image1, open(rubiks_side, 'rb') as image2:
            result = requests.post("http://127.0.0.1:5000", files={
                "image1": image1,
                "image2": image2
            })
        json = result.json()
        self.assertTrue('estimation' in json)
        self.assertAlmostEqual(json['estimation']['width'], 5, delta=5*self.accuracy)
        self.assertAlmostEqual(json['estimation']['height'], 5, delta=5*self.accuracy)
        self.assertAlmostEqual(json['estimation']['depth'], 3.4, delta=5*self.accuracy)

    def test_phone_box(self):
        with open(phone_box_front, 'rb') as image1, open(phone_box_side, 'rb') as image2:
            result = requests.post("http://127.0.0.1:5000", files={
                "image1": image1,
                "image2": image2
            })
        json = result.json()
        self.assertTrue('estimation' in json)
        self.assertAlmostEqual(json['estimation']['width'], 15.7, delta=15.7*self.accuracy)
        self.assertAlmostEqual(json['estimation']['height'], 8.5, delta=15.7*self.accuracy)
        self.assertAlmostEqual(json['estimation']['depth'], 4.7, delta=15.7*self.accuracy)


if __name__ == '__main__':
    unittest.main()
