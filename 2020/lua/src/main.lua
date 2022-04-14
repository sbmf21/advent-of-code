require('data')

local function run(fun)

    local start = os.clock()
    local value = fun()
    local time = os.clock() - start

    return value, time
end

local function runDay(day)
    local start = os.clock()
    require(('day%d.functions'):format(day))
    local time = os.clock() - start

    local value1, time1 = run(part1)
    local value2, time2 = run(part2)

    return value1, value2, time, time1, time2
end

for nr = 1, #days do
    local day = days[nr]
    local value1, value2, time, time1, time2 = runDay(day.nr)

    day.value1 = value1
    day.value2 = value2
    day.timeTotal = time + time1 + time2
    day.timeSetup = time
    day.time1 = time1
    day.time2 = time2
end

require('util.report')

report(days)
