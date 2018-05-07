'''  program to automate the automate the login and browser surfing '''

from selenium import webdriver

# to open firefox webbrowser and maximize the window
browser = webdriver.Firefox(executable_path='C:/Users/admin/Downloads/geckodriver-v0.20.1-win32/geckodriver.exe')
browser.maximize_window()

#connect to the specific ip address
browser.get("http://192.168.1.1:8090/httpclient.html")

assert browser.page_source.find("Cyberoam Captive Portal")

browser.quit()


