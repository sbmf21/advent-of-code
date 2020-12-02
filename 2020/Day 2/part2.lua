require 'functions'

local function isValid(password)
    local min = password.password:sub(password.min, password.min)
    local max = password.password:sub(password.max, password.max)

    local hasMin = min == password.char
    local hasMax = max == password.char

    if(hasMin and hasMax) then return false end
    return hasMin or hasMax
end

passwords = parsePasswords('passwords.txt')

count = 0

for k, v in ipairs(passwords) do
    if isValid(v) then
        count = count + 1
    end
end

print(count)
