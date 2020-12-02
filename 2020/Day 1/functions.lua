local functions = {}

function parseNumbers()
    local lines = io.lines('numbers.txt')
    local numbers = {}

    for line in lines do
        numbers[#numbers + 1] = tonumber(line)
    end

    return numbers
end