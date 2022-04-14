
local function countSlopes()
    local total = count(1, 1)
    local counts = {
        part1(), --3, 1
        count(5, 1),
        count(7, 1),
        count(1, 2)
    }

    for i = 1, #counts do
        total = total * counts[i]
    end

    return total
end

function part2()
    return countSlopes()
end
