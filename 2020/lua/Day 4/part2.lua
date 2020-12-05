require 'functions'

local keys = {
    byr = function (value) return between(value, 1920, 2002) end,
    iyr = function(value) return between(value, 2010, 2020) end,
    eyr = function(value) return between(value, 2020, 2030) end,
    hgt = function(value)
        if value:find('cm') then
            return subBetween(value, 150, 193)
        elseif value:find('in') then
            return subBetween(value, 59, 76)
        end
        
        return false
    end,
    hcl = function(value)
        if value:sub(0, 1):match('#') and value:len() == 7 then
            for i = 2,7 do
                if not value:sub(i, i):match('[abcdef%d]') then
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
            return between(value, 0, 999999999)
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
