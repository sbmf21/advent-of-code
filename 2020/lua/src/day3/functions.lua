local function calcIndex(current, line)
    -- lua starts at index 1, not at 0
    return (current % #line) + 1
end

local function findChar(current, line)
    local index = calcIndex(current, line)

    return line:sub(index, index)
end

local function readMap()
    local map = {}

    for line in io.lines('../../../advent-of-code-input/2020/input/day3.txt') do
        map[#map + 1] = line
    end

    return map
end

function count(xStep, yStep)
    local trees = 0;
    local x = 0;
    local map = readMap()

    for y = 1, #map, yStep do
        if findChar(x, map[y]) == '#' then
            trees = trees + 1
        end
        x = x + xStep
    end

    return trees
end

require 'day3.part1'
require 'day3.part2'
