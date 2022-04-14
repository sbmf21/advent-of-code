
local function sub(password, val)
    return password:sub(val, val)
end

local function isValid(password)
    local hasMin = sub(password.password, password.min) == password.char
    local hasMax = sub(password.password, password.max) == password.char

    if(hasMin and hasMax) then 
        return false
    end

    return hasMin or hasMax
end

function part2()
    return numValid(isValid)
end
