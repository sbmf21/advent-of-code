function testDay(day, expect1, expect2)

    require('day' .. day .. '.functions')

    print('Testing day ' .. day)

    value1 = part1()

    if value1 ~= expect1 then
        error('Part 1: ' .. value1 .. ' did not match expected ' .. expect1)
    end

    value2 = part2()

    if value2 ~= expect2 then
        error('Part 2: ' .. value2 .. ' did not match expected ' .. expect2)
    end

    print("ok\n")
end

days = {
    { part1 = 1019904, part2 = 176647680 }, -- day 1
    { part1 = 515, part2 = 711 }, -- day 2
    { part1 = 191, part2 = 1478615040 }, -- day 3
    { part1 = 247, part2 = 145 }, -- day 4
    { part1 = 922, part2 = 747 }, -- day 5
    { part1 = 6903, part2 = 3493 }, -- day 6
}

for day = 1, #days do
    data = days[day]
    testDay(day, data.part1, data.part2)
end
