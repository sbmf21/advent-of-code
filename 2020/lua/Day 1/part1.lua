require 'functions'

local function find2020(numbers)
    for x, a in ipairs(numbers) do
        for y, b in ipairs(numbers) do
            if x ~= y and a + b == 2020 then
                print('Numbers: ' .. a .. ', ' .. b)
                print('Answer : ' .. a * b)

                return
           end
        end
    end
end

find2020(parseNumbers())
