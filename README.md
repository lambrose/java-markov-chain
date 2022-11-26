# Markov Chain Text Generator Algorithm
A technique of generating a new sequence of text based on the probability of reoccurring text and random selection.

# My Algorithm Approach
 1. Read all contents of a file and store it as a string.
    1. Use a InputStreamReader and BufferedReader to read the file.
    2. Utilise a stringBuilder for the string concatenation.
    3. Return the final string without any leading or trailing whitespace.

 2. Create a prefix and suffix table. The key is the prefix and value is the suffix.
    1. Convert the file string contents into a character array, by using '.split()' with a white space delimiter.
    2. Iterate through the array and append string values to the key and value stringBuilders to formulate the prefix and suffix depending on the user input requirements.
    3. Add the prefix as the HashMap key and add the suffix to the corresponding key ArrayList.

 3. Select a random prefix for the start position,
    1. If the prefix-suffix table is not empty, select a random key by getting all the keys and using the Math random integer function to select an index from the key array.

 4. Generate the new text sequence and return a string.
    1. Add the start prefix to the stringBuilder.
    2. While the max output length is not reached, keep iterating and append the suffix to the output.
    3. To create a new prefix, split the prefix by n amount of words and join the top remaining string with the suffix.
    4. Repeat the process in the loop.

# REST API
    - POST Request
    - Endpoint: http://localhost:8080/api/v1/algorithm/markov
    - Request payload: FormData
        - file:         Text File
        - prefixSize:   Integer
        - suffixSize:   Integer
        - outputSize:   Integer
    - Response payload: JSON

        {
            "fileName": "file.txt",
            "outputSize": 123,
            "outputText": "Text..."
        }


#If I had more time, I would...
1. Create more global exception handlers and exceptions.
2. Implement regex for string cleaning and better search capabilities.
3. Populate the HashMap as the file is read.
4. Spend more time on the text generating function.
5. Include logging in the debug layer.
6. Implement OWASP.