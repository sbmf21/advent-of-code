function parseNumbers()
    local lines = io.lines('../../resources/input/day1.txt')
    local numbers = {}

    for line in lines do
        numbers[#numbers + 1] = tonumber(line)
    end

    return numbers
end

require 'day1.part1'
require 'day1.part2'
