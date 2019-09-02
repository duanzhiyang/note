count = count + 1  
ngx.say("global variable : ", count)  
local shared_data = ngx.shared.shared_data  
ngx.say(", shared memory : ", shared_data:get("count"))  
ngx.say(", shared newCount : ", shared_data:get("newCount"))  
shared_data:incr("count", 1)  
ngx.say("hello world")