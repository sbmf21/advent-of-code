require('data')

local function test(part, value, expected)
    if value ~= expected then
        error(('Part %d: %d did not match expected %d'):format(part, value, expected))
    end
end

local function testDay(day, expect1, expect2)

    require('day' .. day .. '.functions')

    test(1, part1(), expect1)
    test(2, part2(), expect2)

    print("ok\n")
end

for day = 1, #days do
    local data = days[day]
    print(('Testing day %d'):format(data.nr))
    testDay(data.nr, data.part1, data.part2)
end
