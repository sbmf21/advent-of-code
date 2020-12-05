require 'functions'

local function find2020(numbers)
    for x, a in ipairs(numbers) do
        for y, b in ipairs(numbers) do
            for z, c in ipairs(numbers) do
                if x ~= y and y ~= z and z ~= x and a + b + c == 2020 then
                    print('Numbers: ' .. a .. ', ' .. b .. ', ' .. c)
                    print('Answer : ' .. a * b * c)

                    return
                end
            end
        end
    end
end

find2020(parseNumbers())
