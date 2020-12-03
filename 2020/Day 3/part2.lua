local counts = {
    part1, --3, 1
    count(5, 1),
    count(7, 1),
    count(1, 2)
}
local total = count(1, 1)

for i = 1, #counts do total = total * counts[i] end

print(total)
