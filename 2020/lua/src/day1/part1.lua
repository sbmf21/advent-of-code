local function find2020(numbers)
    for x, a in ipairs(numbers) do
        for y, b in ipairs(numbers) do
            if x ~= y and a + b == 2020 then
                return a * b
            end
        end
    end
end

function part1()
    return find2020(parseNumbers())
end
