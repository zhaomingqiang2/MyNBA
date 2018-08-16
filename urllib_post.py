import urllib

 
values = {"username":"1016903103@qq.com","password":"XXXX"}

data = urllib.parse.urlencode(values) 

url = "https://passport.csdn.net/account/login?from=http://my.csdn.net/my/mycsdn"

request = urllib.request.Request(url,data)

response = urllib2.request.urlopen(request)

print response.read()
