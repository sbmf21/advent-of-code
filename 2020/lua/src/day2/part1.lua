
local function isValid(password)
    _, n = password.password:gsub(password.char, '')
    return n >= password.min and n <= password.max
end

function part1()
    return numValid(isValid)
end
