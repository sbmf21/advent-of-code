local function readMap() return io.lines('map.txt') end
local function sub(index, line) return line:sub(index, index) end
local function calcIndex(current, line) return (current % #line) + 1 end
-- lua starts at index 1, not at 0

function count (xStep, yStep)
    local trees = 0; local x = 0; local y = 0
    local map = readMap()

    for line in map do
        if(y == 0) then
            local index = calcIndex(x, line)
            local char = sub(index, line)

            if char == '#' then trees = trees + 1 end
            x = x + xStep
        end

        y = (y + 1) % yStep
    end

    return trees
end
