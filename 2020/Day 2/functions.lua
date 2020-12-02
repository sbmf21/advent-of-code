local function split(input, match)
    local sparts = {}

    for token in input:gmatch('[^' .. match .. ']+') do
        sparts[#sparts + 1] = token
    end

    if (#sparts == 1) then
        return sparts[1]
    end

    return sparts
end

function parsePasswords(file)
    local lines = io.lines(file)
    local passwords = {}

    for line in lines do
        local parts = split(line, ":")
        local rule = split(parts[1], ' ')
        local counters = split(rule[1], '-')

        passwords[#passwords + 1] = {
            min = tonumber(counters[1]),
            max = tonumber(counters[2]),
            char = rule[2],
            password = split(parts[2], ' ')
        }
    end

    return passwords
end
