package DAS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    private static int count = 0;
    private static int value = 0;
    private static boolean isBalanced = true;
    private static boolean isSameTree = true;
    private static TreeNode newRoot = null;
    private static TreeNode LCA = null;
    private static int goodNodes = 0;
    public static boolean isBinaryTree = true;
    private static int size = 0;


    public static void main(String[] args) {

       char[] chars = {'A','A','A','B','B','B'};
       leastInterval(chars,2);



    }

    //Arrays and Hashing

    /*public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) return false;

        char[] charOfS = new char[26];
        char[] charOfT = new char[26];

        for (int i = 0; i < s.length(); i++) {
            charOfS[s.charAt(i) - 'a']++;
            charOfT[t.charAt(i) - 'a']++;
        }
        char max = maxCharacter(charOfS, s);
        System.out.println(max);

        for (int i = 0; i < charOfS.length; i++) {
            if (charOfS[i] != charOfT[i]) return false;
        }
        return true;
    }*/

    public static int[] twoSum(int a[], int target) {

        HashMap map = new HashMap();

        for (int i = 0; i < a.length; i++) {
            if (!map.containsKey(target - a[i])) map.put(a[i], i);
            else {
                return new int[]{(int) map.get(target - a[i]), i};
            }
        }
        return null;
    }
    public static List<List<String>> groupAnagrams(String strs[]) {

        HashMap<String, List<String>> map = new HashMap<>();


        for (int i = 0; i < strs.length; i++) {

            char[] tempArray = strs[i].toCharArray();
            Arrays.sort(tempArray);
            String temp = new String(tempArray);


            if (!map.containsKey(temp)) {
                List<String> tempList = new ArrayList();
                tempList.add(strs[i]);
                map.put(temp, tempList);
            } else {
                List<String> tempList = map.get(temp);
                tempList.add(strs[i]);
                map.put(temp, tempList);
            }
        }
        List<List<String>> list = new ArrayList<List<String>>(map.values());
        return list;
    }
    public static int[] topKFrequent(int[] nums, int k) {

        Arrays.sort(nums);
        HashMap<Integer, List<Integer>> map = new HashMap();
        int i = 0;

        while (i < nums.length) {
            int j = i + 1;
            int count = 1;
            while (j < nums.length && nums[i] == nums[j]) {
                count++;
                j++;
            }
            if (!map.containsKey(count)) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[i]);
                map.put(count, temp);
            } else {
                List<Integer> temp = map.get(count);
                temp.add(nums[i]);
                map.replace(count, temp);
            }
            i = j;
        }

        int topKFrequent[] = new int[k];
        i = 0;

        while (i < k) {
            int j = nums.length;
            while (i < k && j > 0) {
                if (map.containsKey(j)) {
                    int e = 0;
                    while (e < map.get(j).size() && i < k) {
                        topKFrequent[i] = map.get(j).get(e);
                        i++;
                        e++;
                    }
                }
                j--;
            }
        }
        return topKFrequent;
    }
    public static int[] topKFrequent1(int[] nums, int k) {
        //1,1,1,2,2,3
        Arrays.sort(nums);
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int max = 1;

        int i = 0;
        while (i < nums.length) {

            int j = i + 1;
            while (j < nums.length && nums[i] == nums[j]) j++;

            if (!map.containsKey(j - i)) {
                List<Integer> list = new ArrayList<>();
                list.add(nums[i]);
                map.put(j - i, list);
            } else {
                List<Integer> list = map.get(j - i);
                list.add(nums[i]);
                map.replace(j - i, list);
            }
            max = Math.max(j - i, max);
            i = j;
        }
        int topK[] = new int[k];
        int count = 0;

        while (count != k) {

            if (map.containsKey(max)) {
                List<Integer> list = map.get(max);
                System.out.println(list);
                int j = 0;
                while (j < list.size() && j < k) {
                    topK[j + count] = list.get(j);
                    j++;
                }
                count += j;
            }
            max--;
        }
        return topK;
    }
    public static int[] productExceptSelf(int[] nums) {

        int prefix = 1;
        int postfix = 1;
        int answers[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            answers[i] = prefix;
            prefix *= nums[i];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            answers[i] *= postfix;
            postfix *= nums[i];
        }
        return answers;
    }
    public static int longestConsecutiveSequence(int nums[]) {

        if (nums.length == 0) return 0;
        int max = 1;

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        //System.out.println(set);

        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i] - 1)) {
                int temp = 1;
                int k = 1;
                while (set.contains(nums[i] + k)) {
                    k++;
                    temp++;
                }
                max = Math.max(temp, max);
            }
            if (max > nums.length / 2) return max;

        }
        return max;
    }
    public static boolean containsDuplicate(int[] nums) {
        HashMap map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) return true;
            else {
                map.put(nums[i], i);
            }
        }
        return false;
    }
    public static int removeDuplicates(int[] nums) {

        int i = 0;
        int j = i + 1;

        while (j < nums.length) {
            if (nums[i] == nums[j]) {
                j++;
            } else {
                nums[i + 1] = nums[j];
                i++;
            }
        }

        return i + 1;

    }
    public static int removeElement(int[] nums, int val) {

        int i = 0;
        int j = i;
        int swaps = 0;

        while (j < nums.length) {
            if (nums[j] == val) {
                j++;
            } else {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j = i;
                swaps++;
            }
        }


        return swaps;
    }
    public static int[] getConcatenation(int[] nums) {

        int concatedNums[] = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            concatedNums[i] = nums[i];
            concatedNums[i + nums.length] = nums[i];
        }
        return concatedNums;
    }

    //Kadane's algorithim
    public static int maxSubArray(int nums[]) {

        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[i] + sum) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }
            if (sum > max) max = sum;
        }
        return max;
    }
    public static int maxTurbulenceSize(int nums[]) {

        int i = 0;
        int max = 1;
        int temp = 1;
        boolean greaterThan = false, lessThan = false;

        while (i < nums.length - 1) {

            if ((i == 0 && nums[i] > nums[i + 1]) || (nums[i] > nums[i + 1] && !lessThan)) {
                greaterThan = false;
                lessThan = true;
                temp++;
                i++;
            } else if ((i == 0 && nums[i] < nums[i + 1]) || (nums[i] < nums[i + 1] && !greaterThan)) {
                greaterThan = true;
                lessThan = false;
                temp++;
                i++;
            } else {
                greaterThan = false;
                lessThan = false;
                temp = 1;
                if (nums[i] == nums[i + 1]) i++;
            }
            max = Math.max(temp, max);
        }
        return max;
    }

    //Two pointers
    public static boolean isPalindrome(String str) {
        str = str.toLowerCase();
        str = str.replaceAll("\\s", "");
        str = str.replaceAll("[^a-zA-Z0-9]", "");
        int i = 0;
        int j = str.length() - 1;

        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
    public static int[] twoSum2(int[] nums, int target) {

        int i = 0;
        int j = nums.length - 1;

        while (i < j) {

            if (nums[i] + nums[j] < target) i++;
            else if (nums[i] + nums[j] > target) j--;
            else {
                return new int[]{i + 1, j + 1};
            }

        }
        return null;
    }
    public static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);
        int i = 0;
        List<List<Integer>> vals = new ArrayList<>();

        while (i + 2 < nums.length) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                int j = i + 1;
                int k = nums.length - 1;
                int complement = 0 - nums[i];
                List<Integer> temp = new ArrayList<>();

                while (j < k) {
                    if (nums[j] + nums[k] < complement) j++;
                    else if (nums[j] + nums[k] > complement) k--;
                    else {
                        vals.add(List.of(nums[i], nums[j], nums[k]));
                        while (j < k && nums[j + 1] == nums[i]) j++;
                        while (k > j && nums[k] == nums[k - 1]) k--;
                        j++;
                        k--;
                    }
                }
            }
            i++;
        }
        return vals;
    }
    public static int maxArea(int height[]){

        int max = 0;
        int i = 0;
        int j = height.length - 1;

        while(i < j){
            int area;
            if(height[i] < height[j]){
                area = height[i] * (j-i);
                i++;
            }else{
                area = height[j] * (j- i);
                j--;
            }
            if(area > max) max = area;
        }
        return max;
    }


    // Sliding window

    public static boolean containsNearbyDuplicate(int[] nums, int k) {

        if (nums.length == 0) return false;
        HashMap<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], i);
            else {
                if (i - map.get(nums[i]) <= k) return true;
                else {
                    map.replace(nums[i], i);
                }
            }
        }
        return false;
    }
    public static boolean containsNearbyDuplicate1(int[] nums, int k) {

        Set<Integer> set = new HashSet<>();
        int i = 0;
        set.add(nums[i]);
        int j = i + 1;
        while (i < nums.length) {
            while (j < nums.length && j - i <= k && j != i) {
                if (set.contains(nums[j])) return true;
                set.add(nums[j]);
                j++;
            }
            set.remove(nums[i]);
            i++;
        }
        return false;
    }
    public static int numOfSubarrays(int[] arr, int k, int threshold) {

        int max = 0;
        int sum = arr[0];
        int i = 0;
        int j = i + 1;

        while (i + k <= arr.length) {

            while (j - i <= k - 1) {
                sum += arr[j];
                j++;
            }
            int avg = sum / (j - i);
            if (avg >= threshold) max++;
            sum -= arr[i];
            i++;
        }
        return max;
    }
    public static int minSubArrayLen(int target, int[] nums) {

        int i = 0;
        int j = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        while (j < nums.length) {

            System.out.println(sum);

            sum += nums[j];
            j++;

            if (sum >= target) {
                while (sum >= target) {
                    System.out.println(sum);
                    min = Integer.min(min, j - i);
                    sum -= nums[i];
                    i++;
                }
            }
        }

        if (Integer.MIN_VALUE == min) return 0;


        return min;
    }
    public static int lengthOfLongestSubstring(String str) {
        if (str.length() == 0) return 0;

        List<Character> list = new ArrayList<>();
        int max = 1;


        for (int i = 0; i < str.length(); i++) {

            if (list.contains(str.charAt(i))) {
                max = Math.max(max, list.size());
                list.subList(0, list.indexOf(str.charAt(i)) + 1).clear();
            }
            list.add(str.charAt(i));
        }


        return max = Math.max(max, list.size());
    }




    //Stacks
    public static boolean isValid(String s) {

        Stack<Character> stack = new Stack();
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                stack.push(s.charAt(i));
            } else {
                if (stack.isEmpty()) return false;
                else if (s.charAt(i) == ')' && stack.pop() != '(') return false;
                else if (s.charAt(i) == '}' && stack.pop() != '{') return false;
                else if (s.charAt(i) == ']' && stack.pop() != '[') return false;
            }
        }
        return stack.isEmpty();
    }
    public static int callPoints(String operations[]) {

        Stack<Integer> stack = new Stack();
        int total = 0;

        for (int i = 0; i < operations.length; i++) {
            if (operations[i].equals("+")) {
                int temp = stack.pop();
                int sum = temp + stack.peek();
                stack.push(temp);
                stack.push(sum);
                total += sum;
            } else if (operations[i].equals("D")) {
                total += stack.peek() * 2;
                stack.push(stack.peek() * 2);
            } else if (operations[i].equals("C")) {
                total -= stack.peek();
                stack.pop();
            } else {
                stack.push(Integer.parseInt(operations[i]));
                total += Integer.parseInt(operations[i]);
            }
        }
        /*while(!stack.isEmpty()){
            total += stack.pop();
        }*/
        return total;
    }
    public static int evalRPN(String[] tokens){

        if(tokens.length == 1) return Integer.parseInt(tokens[0]);

        Stack<Integer> stack = new Stack<>();
        int total = 0;

        for(int i = 0; i < tokens.length; i++) {

            if(tokens[i].equals("+")){
                total = (stack.pop() + stack.pop());
                stack.add(total);
            }
            else if(tokens[i].equals("-")){
                total = -1*(stack.pop() - stack.pop());
                stack.add(total);
            }
            else if(tokens[i].equals("*")){
                total = (stack.pop() * stack.pop());
                stack.add(total);
            }
            else if(tokens[i].equals("/")){
                int divisor = stack.pop();
                int dividend = stack.pop();
                total = dividend/divisor;
                stack.add(total);
            }else{
                stack.add(Integer.parseInt(tokens[i]));
            }
        }
        return total;
    }
    //later try to make this faster
    public static int[] dailyTemperatures(int[] temperatures) {

        int i = 0;
        Stack<Integer>stack = new Stack<>();
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for(int j = 0; j < temperatures.length; j++){
            if(!map.containsKey(temperatures[j])){
                Queue<Integer> queue = new LinkedList<>();
                queue.add(j);
                map.put(temperatures[j],queue);
            }else{
                map.get(temperatures[j]).add(j);
            }

        }
        System.out.println(map);


        int answers[] = new int[temperatures.length];
        while(i < temperatures.length){

            stack.add(temperatures[i]);

            int j = i + 1;
            while(j < temperatures.length && !stack.isEmpty()){
                if(temperatures[j] > stack.peek()){
                    answers[map.get(stack.peek()).peek()] = j - map.get(stack.peek()).peek();
                    map.get(stack.peek()).poll();
                    stack.pop();
                }else{
                    stack.add(temperatures[j]);
                    j++;
                }
            }
            i = j;
        }
        return answers;
    }


    //Linked List
    public static int findDuplicate(int[] nums) {

        int a[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (a[nums[i]] == 0) a[nums[i]] = 1;
            else return nums[i];
        }
        return -1;
    }
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {

        ListNode ptr1 = l1;
        ListNode ptr2 = l2;

        ListNode l3 = new ListNode(0);
        ListNode curr = l3;

        int remainder = 0;

        while (l1 != null || l2 != null) {

            int val1, val2;
            if (l1 == null) {
                val1 = 0;
            } else {
                val1 = l1.val;
            }
            if (l2 == null) {
                val2 = 0;
            } else {
                val2 = l2.val;
            }

            int total = val1 + val2 + remainder;
            if (total >= 10) {
                total %= 10;
                remainder = 1;
            } else remainder = 0;

            curr.next = new ListNode(total);
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            curr = curr.next;
            System.out.println(curr.val);
        }

        if (remainder == 1) {
            curr.next = new ListNode(1);
        }

        return l3.next;
    }
    public static void reOrder(ListNode head) {

        ListNode curr = head;

        while (curr.next != null && curr.next.next != null) {
            ListNode tail = findTail(curr);
            ListNode temp = curr.next;
            curr.next = tail;
            tail.next = temp;
            printList(curr);

            curr = curr.next.next;
        }
    }
    public static ListNode findTail(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        ListNode temp = new ListNode(curr.val);
        prev.next = null;
        return temp;
    }
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {

        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;

        ListNode fast = head;
        int i = 1;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            i += 2;
        }
        if (fast.next != null) {
            fast = fast.next;
            i++;
        }

        ListNode slow = head;
        System.out.println(i - n);

        if (i == n) {
            dummyhead = head.next;
            head = null;
            return dummyhead;
        }

        for (int j = 1; j < i - n; j++) {
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummyhead.next;
    }
    public ListNode deleteDuplicates(ListNode head) {

        ListNode curr = head;

        while(curr.next != null) {

            if(curr.next.val == curr.val) curr.next = curr.next.next;
            else curr = curr.next;

        }
        return head;
    }

    //iterative approach
    public static ListNode reverse(ListNode head) {

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode currNext = curr.next;
            curr.next = prev;
            prev = curr;
            curr = currNext;
        }
        return prev;
    }
    //recursive approach
    public static ListNode reverseLinkedList(ListNode head) {
        return reversing(null, head);
    }
    public static ListNode reversing(ListNode prev, ListNode curr) {
        if (curr == null) return prev;
        ListNode currNext = curr.next;
        curr.next = prev;
        prev = curr;
        curr = currNext;
        return reversing(prev, curr);
    }


    //dynamic programming
    public static int fib(int n) {

        if (n == 0) return 0;
        if (n == 1) return 1;

        int fib[] = new int[n];
        fib[0] = 0;
        fib[1] = 1;
        return fibTopDown(n, fib);
    }
    public static int fibTopDown(int n, int[] fib) {
        if (n == 0) return 0;
        else if (n == 1) return 1;

        fib[n - 1] = fibTopDown(n - 2, fib) + fibTopDown(n - 1, fib);
        return fib[n - 1];
    }
    public static int fibBottomDown(int n) {

        int fibSequence[] = new int[n + 1];
        fibSequence[0] = 0;
        fibSequence[1] = 1;

        for (int i = 2; i <= n; i++) {
            fibSequence[i] = fibSequence[i - 2] + fibSequence[i - 1];
        }
        return fibSequence[n];
    }

    //quick sort
    public static void quickSort(int a[], int left, int right) {

        if (left < right) {

            int pivot = partition(a, left, right);
            quickSort(a, left, pivot - 1);
            quickSort(a, pivot + 1, right);
        }
    }
    public static int partition(int a[], int left, int right) {

        int pivot = a[right];
        int i = left - 1;

        for (int j = i + 1; j < right; j++) {

            if (a[j] <= pivot) {

                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        int temp = a[right];
        a[right] = a[i + 1];
        a[i + 1] = temp;


        return i + 1;
    }

    //counting sort
    public static void countingSort(int a[]) {

        int max = 0;

        //find max
        for (int i = 0; i < a.length; i++) max = Math.max(a[i], max);

        int count[] = new int[max + 1];


        for (int i = 0; i < a.length; i++) count[a[i]]++;

        int prefix = 0;
        for (int i = 0; i < count.length; i++) {
            prefix += count[i];
            count[i] = prefix;
        }

        int output[] = new int[a.length];

        for (int i = a.length - 1; i >= 0; i--) {

            output[count[a[i]] - 1] = a[i];
            count[a[i]]--;

        }

        for (int i = 0; i < output.length; i++) System.out.print(output[i] + " ");


    }

    //merge sort
    public static void mergeSort(int a[]) {
        if (a.length < 2) return;
        int mid = a.length / 2;
        int left[] = new int[mid];
        int right[] = new int[a.length - mid];

        for (int i = 0; i < mid; i++) left[i] = a[i];
        for (int i = 0; i < a.length - mid; i++) right[i] = a[mid + i];

        mergeSort(left);
        mergeSort(right);
        merge(a, left, right);
    }
    public static void merge(int a[], int left[], int right[]) {

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < left.length) {
            a[k] = left[i];
            k++;
            i++;
        }

        while (j < right.length) {
            a[k] = right[j];
            k++;
            j++;
        }


    }


    //heaps
    public static int lastStoneWeight(int[] stones) {

        buildMaxHeap(stones);
        int size = stones.length;
        while (size > 1) {
            int max = extractMaxHeap(stones, size);
            //System.out.println(max);
            size--;
            if (stones[0] == max) {
                extractMaxHeap(stones, size);
                size--;
            } else if (stones[0] < max) {
                stones[0] = Math.abs(max - stones[0]);
                heapify(stones, size, 0);
            }
            //System.out.println(size);
        }
        if (size != 0) return extractMaxHeap(stones, size);
        else return 0;
    }
    public static int findKthLargest(int[] nums, int k) {

        buildMaxHeap(nums);
        int i = 0;
        int size = nums.length;
        while (i < k - 1) {
            extractMaxHeap(nums, size);
            size--;
            i++;
        }
        return nums[0];
    }
    public static void buildMaxHeap(int[] nums) {

        for (int i = (nums.length / 2) - 1; i >= 0; i--) {
            heapify(nums, nums.length, i);
        }
    }
    public static void heapify(int[] nums, int size, int index) {

        int root = index;
        int left = (index * 2) + 1;
        int right = (index * 2) + 2;

        if (left < size && nums[left] > nums[root]) root = left;
        if (right < size && nums[right] > nums[root]) root = right;

        if (root != index) {
            int temp = nums[root];
            nums[root] = nums[index];
            nums[index] = temp;
            heapify(nums, size, root);
        }

    }
    public static int extractMaxHeap(int nums[], int size) {
        if (size == 0) return -1;
        int min = nums[0];
        nums[0] = nums[size - 1];
        size--;
        heapify(nums, size, 0);
        return min;
    }
    public static ListNode mergeKLists(ListNode[] lists) {
        int size = 0;
        PriorityQueue queue = combineLists(lists);
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;

        while (!queue.isEmpty()) {
            curr.next = new ListNode((Integer) queue.poll());
            curr = curr.next;
        }
        return dummyHead.next;
    }
    public static PriorityQueue combineLists(ListNode[] lists) {

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < lists.length; i++) {
            ListNode temp = lists[i];
            while (temp != null) {
                queue.add(temp.val);
                temp = temp.next;
            }
        }
        System.out.println(queue);
        return queue;
    }
    public static int[][] kClosest(int[][] points, int k) {

        buildMinHeap(points);
        int closestPoints [][] = new int[k][];
        int i = 0;
        int size = points.length;
        printHeap(points);

        while(i < k){
            closestPoints[i] = extractMinHeap(points,size);
            size--;
            i++;
            printHeap(points);
        }
        return closestPoints;
    }
    public static double distance(int x1, int y1, int x2, int y2){
        return Math.sqrt((Math.pow((x1-x2),2) + Math.pow((y1-y2),2)));
    }
    public static void buildMinHeap(int[][] points){
        for(int i = (points.length/2) - 1; i>= 0; i--){
            heapifyAccordingToDistance(points,points.length,i);
        }
    }
    public static void heapifyAccordingToDistance(int[][]points, int size, int index){
        int root = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        if(left < size && distance(points[root][0],points[root][1],0,0) > distance(points[left][0], points[left][1],0,0)){
            root = left;
        }
        if(right < size && distance(points[root][0],points[root][1],0,0) > distance(points[right][0], points[right][1],0,0)){
            root = right;
        }

        if(root != index){
            int [] temp = points[root];
            points[root] = points[index];
            points[index] = temp;
            heapifyAccordingToDistance(points, size, root);
        }
    }
    public static int[] extractMinHeap(int[][]points, int size){
        int min [] = points[0];
        points[0] = points[size - 1];
        size--;
        heapifyAccordingToDistance(points, size, 0);
        return min;
    }
    public static void printHeap(int[][]points){
        for(int i = 0; i < points.length; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(points[i][j] + ",");
            }
            System.out.print("   ");
        }
        System.out.println();
    }


    public static int leastInterval(char[] tasks, int n) {

        if(n == 0) return tasks.length;

        char[] letters = new char[26];
        List<Character>list = new ArrayList<>();

        for (int i = 0; i < tasks.length; i++) {
           if(letters[Character.toLowerCase(tasks[i])-'a'] == 0) list.add(Character.toLowerCase(tasks[i]));
            letters[Character.toLowerCase(tasks[i]) - 'a']++;
        }

        Letter[] heap = new Letter[list.size()];
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (letters[i] != 0){
                Letter letter = new Letter(list.get(i), letters[Character.toLowerCase(list.get(i)) - 'a']);
                heap[j] = letter;
                j++;
            }
        }

        buildCharacterHeap(heap);
        for(int i = 0; i < heap.length; i++) System.out.print(heap[i].letter + " " + heap[i].count + "\n");


        size = heap.length;
        int i = 1;

        List<Character> intervals = new ArrayList<>();
        intervals.add(extractCharacterHeap(heap,size).letter);


        while(size != 0){

            if(heap[0].letter != intervals.get(i - 1)){

                if(i - n < 0){
                    if(!intervals.subList(0,i).contains(heap[0].letter)){
                        intervals.add(extractCharacterHeap(heap,size).letter);
                    }else intervals.add('I');
                }else{
                    if(!intervals.subList(i-n,i).contains(heap[0].letter)){
                        intervals.add(extractCharacterHeap(heap,size).letter);
                    }else intervals.add('I');
                }
            }else intervals.add('I');
            i++;
            System.out.println(intervals);
        }
        System.out.println(intervals);
        return intervals.size();
    }
    public static void buildCharacterHeap(Letter[]letters){
        for(int i = letters.length/2 - 1; i >= 0; i--){
            heapifyCharacterHeap(letters,letters.length,i);
        }
    }
    public static void heapifyCharacterHeap(Letter [] letters, int size, int index){

        int root = index;
        int leftChild = index * 2 + 1;
        int rightChild = index * 2 + 2;

        if(rightChild < size && letters[root].count < letters[rightChild].count) root = rightChild;
        if(leftChild < size && letters[root].count < letters[leftChild].count) root = leftChild;

        if(root != index){
            Letter temp = letters[root];
            letters[root] = letters[index];
            letters[index] = temp;
            heapifyCharacterHeap(letters,size,root);
        }


    }
    public static Letter extractCharacterHeap(Letter[] letters, int size){
        Letter letter = letters[0];
        letters[0].count--;
        if(letters[0].count == 0){
            letters[0] = letters[size - 1];
            size--;
        }
        heapifyCharacterHeap(letters, size, 0);
        return letter;
    }



    //trees
    public static TreeNode insertIntoBST(TreeNode root, int val) {

        if (root == null) root = new TreeNode(val);
        if (val < root.val) root.left = insertIntoBST(root.left, val);
        else if (val > root.val) root.right = insertIntoBST(root.right, val);

        return root;
    }
    public static TreeNode findMinimum(TreeNode root) {
        if (root.left == null) return root;
        TreeNode minimum = findMinimum(root.left);
        return minimum;
    }
    public static TreeNode findMaximum(TreeNode root) {
        if (root.right == null) return root;
        TreeNode maximum = findMaximum(root.right);
        return maximum;
    }
    public static void printPostOrderTraversal(TreeNode root) {
        if (root == null) return;

        printPostOrderTraversal(root.left);
        printPostOrderTraversal(root.right);

        System.out.print(root.val + " ");
    }
    public static void printInOrderTraversal(TreeNode root) {
        if (root == null) return;

        printInOrderTraversal(root.left);
        System.out.print(root.val + " ");
        printInOrderTraversal(root.right);
    }
    public static void printPreOrderTraversal(TreeNode root) {
        if (root == null) return;

        System.out.println(root.val);
        printPreOrderTraversal(root.left);
        printPreOrderTraversal(root.right);
    }
    public static TreeNode invertTree(TreeNode root) {

        if (root == null) return null;

        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        swap(root);
        return root;
    }
    public static void swap(TreeNode root) {
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    public static TreeNode deleteNode1(TreeNode root, int key) {

        //find the node to delete
        //specify the case
        //contiue

        if (root.val < key) root.right = deleteNode1(root.right, key);
        else if (root.val > key) root.left = deleteNode1(root.left, key);
        else {
            if (root.left == null && root.right == null) root = null;
            else if (root.left == null && root.right != null) root = root.right;
            else if (root.left != null && root.right == null) root = root.left;
            else {
                TreeNode minimum = findMinimum(root.right);
                root.val = minimum.val;
                deleteNode1(root.right, minimum.val);
            }
        }
        return root;
    }
    public static int maxDepth1(TreeNode root) {

        if (root == null) return 1;

        int max = 1 + Math.max(maxDepth1(root.left), maxDepth1(root.right));

        return max - 1;
    }
    public static int kthSmallest(TreeNode root, int k) {
        helper1(root, k);
        return value;
    }
    public static void helper1(TreeNode root, int k) {
        if (root == null) return;

        helper1(root.left, k);

        count++;
        if (count == k) value = root.val;

        helper1(root.right, k);
    }
    public static boolean isSameTree(TreeNode p, TreeNode q) {

        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);

    }
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {

        if (root == null) return false;

        if (root.val == subRoot.val) {
            isSameTree(root, subRoot);
        }

        isSubtree(root.left, subRoot);
        isSubtree(root.right, subRoot);

        return isSameTree;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (p.val < root.val && q.val > root.val || (q.val < root.val && p.val > root.val)) return root;
        else if (p.val == root.val) return p;
        else if (q.val == root.val) return q;
        else if ((root.left != null && root.left.val == p.val) || (root.right != null && root.right.val == p.val))
            return p;
        else if ((root.left != null && root.left.val == q.val) || (root.right != null && root.right.val == q.val))
            return q;

        if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        else return lowestCommonAncestor(root.left, p, q);
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return list;

        Queue<TreeNode>queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){

            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size;i++){
                temp.add(queue.peek().val);
                if(queue.peek().left != null) queue.add(queue.peek().left);
                if(queue.peek().right != null) queue.add(queue.peek().right);
                queue.poll();
            }
            list.add(temp);
        }
        return list;
    }
    public static List<Integer> rightSideView(TreeNode root) {

        List<Integer>rightSide = new ArrayList<>();

        if(root == null) return null;
        List<List<Integer>> list = new ArrayList<>();
        if(root == null) return new ArrayList<>();

        Queue<TreeNode>queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){

            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for(int i = 0; i < size;i++){
                temp.add(queue.peek().val);
                if(queue.peek().left != null) queue.add(queue.peek().left);
                if(queue.peek().right != null) queue.add(queue.peek().right);
                queue.poll();
            }
            list.add(temp);
            rightSide.add(temp.get(temp.size()-1));
        }
        return rightSide;
    }
    public static int goodNodes(TreeNode root){
        if(root == null) return 0;
        int max = root.val;
        return goodNodes(root,max);
    }
    public static int goodNodes(TreeNode root, int max){

        if(root == null) return 0;

        if(root.val >= max) {
            goodNodes++;
            max = root.val;
        }
        goodNodes(root.left,max);
        goodNodes(root.right,max);

        return goodNodes;
    }
    public static int minDepth(TreeNode root) {

        if(root == null) return 0;

        int leftMin = 1 + minDepth(root.left);
        int rightMin = 1 + minDepth(root.right);

        if(leftMin != 1 && rightMin != 1) return Math.min(leftMin,rightMin);
        else if(leftMin == 1) return rightMin;
        else return leftMin;


    }
    public static boolean isValidBST(TreeNode root) {
        if(root == null) return false;
        isValidBst(root,Double.MIN_VALUE, Double.MAX_VALUE);
        return isBinaryTree;
    }
    public static void isValidBst(TreeNode root, double min, double max){

        if(root == null) return;

        if(root.left != null && (root.left.val >= root.val || root.left.val <= min)){
            isBinaryTree = false;
            return;
        }
        if(root.right != null && (root.right.val <= root.val || root.right.val >= max)){
            isBinaryTree = false;
            return;
        }

        isValidBst(root.left, min, root.val);
        isValidBst(root.right, root.val, max);
    }

}

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
       this.val = val;
          this.left = left;
          this.right = right;
      }
}

class Letter{

    public char letter;
    public int count;

    public Letter(char letter, int count){
        this.letter = letter;
        this.count = count;
    }


}

