function table_has(table, val)
    for _, value in ipairs(table) do
        if value == val then
            return true
        end
    end

    return false
end

function table_sum(table)
    local acc = 0

    for _, val in ipairs(table) do
        acc = acc + val
    end

    return acc
end

function parseGroups()
    local groups = {};
    local group = {}

    for line in io.lines('../../../input/2020/input/day6.txt') do
        if line == '' then
            groups[#groups + 1] = group
            group = {}
        else
            group[#group + 1] = line
        end
    end

    groups[#groups + 1] = group

    return groups
end

require 'day6.part1'
require 'day6.part2'
