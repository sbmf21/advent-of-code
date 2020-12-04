require 'functions'

local keys = {
    byr = function (value)
        return clamp(value, 1920, 2002)
    end,
    iyr = function(value)
        return clamp(value, 2010, 2020)
    end,
    eyr = function(value)
        return clamp(value, 2020, 2030)
    end,
    hgt = function(value)
        if value:find('cm') then
            value = value:sub(0, value:len() - 2)
            return clamp(value, 150, 193)
        elseif value:find('in') then
            value = value:sub(0, value:len() - 2)
            return clamp(value, 59, 76)
        end
        
        return false
    end,
    hcl = function(value)
        if value:sub(0, 1):match('#') and value:len() == 7 then
            for i = 2,7 do
                if value:sub(i, i):match('[abcdef%d]') then
                else
                    return false
                end
            end

            return true
        end

        return false
    end,
    ecl = function(value)
        for _, v in ipairs(split('amb blu brn gry grn hzl oth', ' ')) do
            if value == v then
                return true
            end
        end

        return false
    end,
    pid = function(value)
        if value:len() == 9 then
            return clamp(value, 0, 999999999)
        end

        return false
    end
}

local function isValid(passport)
    for key, isKeyValid in pairs(keys) do
        if passport[key] ~= null then
            if not isKeyValid(passport[key]) then
                return false
            end
        else
            return false
        end
    end

    return true
end

print(countValidPassports(isValid))
