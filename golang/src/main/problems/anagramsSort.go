package main

import (
	"fmt"
	"sort"
)

func main() {
	b := []string{"Penn", "Teller","Ann", "nePn"}
	sort.Sort(Annagrams(b))
	fmt.Printf("%v\n", b)
}

type RuneSlice []rune

func (p RuneSlice) Len() int           { return len(p) }
func (p RuneSlice) Less(i, j int) bool { return p[i] < p[j] }
func (p RuneSlice) Swap(i, j int)      { p[i], p[j] = p[j], p[i] }

type Annagrams []string

func (p Annagrams) Len() int           { return len(p) }
func (p Annagrams) Swap(i, j int)      { p[i], p[j] = p[j], p[i] }
func (p Annagrams) Less(i, j int) bool { 
	aNorm := normalize(p[i])
	bNorm := normalize(p[j])
	if aNorm == bNorm {
		return p[i] < p[j] 
	}
	return aNorm < bNorm 
}

func normalize(str string) string {
	runes := []rune(str)

    sort.Sort(RuneSlice(runes))

	return string(runes)
}