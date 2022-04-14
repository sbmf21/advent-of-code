require('data')

local function runDay(day)
    day = tostring(day)

    require(('day%d.functions'):format(day))

    print(('Day %d'):format(day))
    print(('Part 1: %d'):format(part1()))
    print(('Part 2: %d'):format(part2()))
    print()
end

for day = 1, #days do
    runDay(days[day].nr)
end
