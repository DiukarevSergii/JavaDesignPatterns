package patterns.dependency_injection;

import java.util.List;
import java.util.Objects;

// To pass the resource into the constructor when creating a new instance.
// This is one form of dependency injection: the dictionary is a dependency of the spell checker
// and is injected into the spell checker when it is created.

// Dependency injection provides flexibility and testability
public class SpellChecker {
    private final /*Lexicon*/ Object dictionary;

    public SpellChecker(/*Lexicon*/ Object dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public boolean isValid(String word) {
        //    ...
        return false;
    }

    public List<String> suggestions(String typo) {
        //...
        return null;
    }
}