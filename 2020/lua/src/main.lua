function runDay(day)
    day = tostring(day)

    require('day' .. day .. '.functions')

    print('Day ' .. day)
    print('Part 1: ' .. part1())
    print('Part 2: ' .. part2())
    print()
end

for day = 1, 6 do
    runDay(day)
end
