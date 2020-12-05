
local function find2020(numbers)
    for x, a in ipairs(numbers) do
        for y, b in ipairs(numbers) do
            for z, c in ipairs(numbers) do
                if x ~= y and y ~= z and z ~= x and a + b + c == 2020 then
                    return a * b * c
                end
            end
        end
    end
end

function part2()
    return find2020(parseNumbers())
end
