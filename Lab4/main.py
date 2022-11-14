states = None
initial_state = None
states_rules = None
final_state_rules = None
current_state = None
characters = None
alphabet = None


def print_menu():
    print("1. Print set of states")
    print("2. Print the alphabet")
    print("3. Print state rules")
    print("4. Print initial state")
    print("5. Print final states")
    print("6. Compute sequence")


def print_states():
    print(states)


def print_alphabet():
    print(alphabet)


def print_state_rules():
    print(final_state_rules)


def print_initial_state():
    print(initial_state)


def print_final_states():
    print(final_states)


def compute_sequence():
    global current_state
    sequence = input("Provide sequence:")
    sequence = sequence.replace("\n", "").split(",")
    for character in sequence:
        found = False
        for state_rule in final_state_rules:
            if state_rule["source"] == current_state and state_rule["character"] == character:
                current_state = state_rule["destination"]
                found = True
                break
        if not found:
            raise Exception("STATE RULE NOT FOUND")

    if current_state in final_states:
        print(current_state + " -> CORRECT SEQUENCE")
    else:
        print(current_state + " -> INCORRECT SEQUENCE")


if __name__ == '__main__':
    file = open("FA.in", "r+")
    states = file.readline().replace("\n", "")
    states = states.split(",")

    alphabet = file.readline().replace("\n", "")
    alphabet = alphabet.split(",")

    initial_state = file.readline().replace("\n", "")

    final_states = file.readline().replace("\n", "")
    final_states = final_states.split(",")

    states_rules = file.readline().replace("\n", "")
    states_rules = states_rules.split(",")

    final_state_rules = []

    for states_rule in states_rules:
        states_rule = states_rule.split(";")
        final_state_rules.append({"source": states_rule[0], "character": states_rule[1], "destination": states_rule[2]})

    current_state = initial_state
    menu = {"1": print_states, "2": print_alphabet, "3": print_state_rules, "4": print_initial_state,
            "5": print_final_states, "6": compute_sequence}
    while True:
        current_state = initial_state
        print_menu()
        option = input("Choose: ")
        menu[option]()
