require('util.timings')

local longest = { nr = 0, value1 = 0, value2 = 0, timeTotal = 0, timeSetup = 0, time1 = 0, time2 = 0 }
local headers = {
    nr = "Day",
    value1 = "Part 1",
    value2 = "Part 2",
    timeTotal = "Total (Time)",
    timeSetup = "Setup (Time)",
    time1 = "Part 1 (Time)",
    time2 = "Part 2 (Time)",
}

local function testLengthsForDay(day, field)
    local len = string.len(tostring(day[field]))
    if len > longest[field] then
        longest[field] = len
    end
end

local function testLengths()
    for attr, value in pairs(headers) do
        longest[attr] = string.len(value)
    end

    for nr = 1, #days do
        for attr, _ in pairs(longest) do
            testLengthsForDay(days[nr], attr)
        end
    end
end

local function line(data, left, separator, right)
    local function cell(field)
        local value = tostring(data[field])
        return string.rep(' ', longest[field] - string.len(value)) .. value
    end

    left = left or '│ '
    separator = separator or ' │ '
    right = right or ' │'

    print(('%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s'):format(
        left,
        cell('nr'),
        separator,
        cell('value1'),
        separator,
        cell('value2'),
        separator,
        cell('timeTotal'),
        separator,
        cell('timeSetup'),
        separator,
        cell('time1'),
        separator,
        cell('time2'),
        right
    ))
end

local function buildSeparators()

    local lines = {}

    for key, len in pairs(longest) do
        lines[key] = string.rep('─', len)
    end

    return lines
end

local function reportDays(days)
    for nr = 1, #days do
        line(days[nr])
    end
end

function report(days)

    handleTimings(days)
    testLengths()
    local separators = buildSeparators()

    line(separators, '┌─', '─┬─', '─┐')
    line(headers)
    line(separators, '├─', '─┼─', '─┤')
    reportDays(days)
    line(separators, '└─', '─┴─', '─┘')
end
