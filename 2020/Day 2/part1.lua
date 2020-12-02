require 'functions'

local function isValid(password)
    _, n = password.password:gsub(password.char, '')
    return n >= password.min and n <= password.max
end

passwords = parsePasswords('passwords.txt')
count = 0

for k, v in ipairs(passwords) do
    if isValid(v) then
        count = count + 1
    end
end

print(count)
