require 'functions'

local function isValid(password)
    _, n = password.password:gsub(password.char, '')
    return n >= password.min and n <= password.max
end

print(numValid(isValid))
