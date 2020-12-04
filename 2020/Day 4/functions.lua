function split(input, match)
    local sparts = {}

    for token in input:gmatch('[^' .. match .. ']+') do
        sparts[#sparts + 1] = token
    end

    return sparts
end

local function addCurrent(passports, current)
    passports[#passports + 1] = current

    return {}
end

local function parseLine(current, line)
    local fields = split(line, ' ')
 
    for _, field in ipairs(fields) do
        local parts = split(field, ':')
       
        current[parts[1]] = parts[2]
    end
end

 function parsePassports()
    local lines = io.lines('passports.txt')
    local passports = {}; local current = {}

    for line in lines do
        if line == '' then
            current = addCurrent(passports, current)
        end

        parseLine(current, line)
    end

    addCurrent(passports, current)

    return passports
end

function countValidPassports(isValid)
    local count = 0

    for _, passport in ipairs(parsePassports()) do
        if isValid(passport) then
            count = count + 1
        end
    end

    return count
end

function subBetween(value, min, max)
    value = value:sub(0, value:len() - 2)

    return between(value, min, max)
end

function between(value, min, max)
    value = tonumber(value)

    return value >= min and value <= max
end
