require 'functions'

local function findHighestId()
    local passes = parseBoardingPasses()
    local id = passes[1].id
    
    for i = 2, #passes do
        local pid = passes[i].id
    
        if pid > id then
            id = pid
        end
    end

    return id
end

print(findHighestId())
