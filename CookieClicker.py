from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

import threading
import time
import random


PATH = "C:\Program Files (x86)\chromedriver_win32\chromedriver.exe"
driver = webdriver.Chrome(PATH)

def cookie_clicker_thread():
    while True: 
        cookie = driver.find_element(By.ID,"bigCookie")
        cookie.click()
        
def buy_items_thread():
    while True:
        upgrades = driver.find_elements(By.XPATH, "//div[@class='crate upgrade enabled']")
        if upgrades:
            random.choice(upgrades).click()
            
        
        items = driver.find_elements(By.XPATH, "//div[@class='product unlocked enabled']")
        if items:
            items[-1].click()
            
        time.sleep(.5)
        
            


driver.maximize_window()
driver.get("https://orteil.dashnet.org/cookieclicker/")

try:
    lang = WebDriverWait(driver,15).until(
        EC.presence_of_element_located((By.ID,"langSelect-EN"))
    )
    lang.click()
except:
    driver.quit()

time.sleep(5)

driver.find_element(By.XPATH, "//a[@class='cc_btn cc_btn_accept_all']").click()

cookie_clicker = threading.Thread(target=cookie_clicker_thread)
cookie_clicker.start()

items_buyer = threading.Thread(target=buy_items_thread)
items_buyer.start()