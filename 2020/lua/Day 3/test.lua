#!/usr/local/bin/lua

require 'functions'

local part1 = part1(); local expect1 = 191
local part2 = part2(); local expect2 = 1478615040

if part1 ~= expect1 then error('Part 1: '..part1..' did not match expected '..expect1) end
if part2 ~= expect2 then error('Part 2: '..part2..' did not match expected '..expect2) end
