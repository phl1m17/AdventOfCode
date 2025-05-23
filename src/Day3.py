import re
print(sum(int(a)*int(b) for a, b in re.findall("mul\(([0-9]{1,3}),([0-9]{1,3})\)", open("src/text.txt").read())))

total = 0
enabled = True

for match in re.finditer("mul\((\d{1,3}),(\d{1,3})\)|don't\(\)|do\(\)|undo\(\)", open("src/text.txt").read()):
    token = match.group(0)
    
    if token.startswith("mul"):
        a, b = int(match.group(1)), int(match.group(2))
        if enabled:
            total += a * b
    elif token == "don't()":
        enabled = False
    elif token == "do()":
        enabled = True

print(total)
